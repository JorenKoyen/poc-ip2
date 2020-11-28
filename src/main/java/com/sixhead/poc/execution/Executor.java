package com.sixhead.poc.execution;

import com.sixhead.poc.Tuple;
import com.sixhead.poc.execution.handlers.SpecificationHandler;
import com.sixhead.poc.util.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Executor {
  private final Logger log = Logger.getLogger("Executor");
  private final Map<Class<? extends GameEvent>, SpecificationHandler<? extends GameEvent>> handlers;
  private final Queue<Tuple<? extends GameEvent, Initiator>> queue;
  private Object[] buffer;
  private int pointer;

  public Executor() {
    this.queue = new LinkedList<>();
    this.handlers = new HashMap<>();
  }

  public void setBufferState(Object o) {
    this.buffer[this.pointer] = o;
    this.pointer = (this.pointer + 1) % this.buffer.length;
  }

  public Object getLastState() {
    int previousPointer = this.pointer - 1;
    previousPointer = previousPointer < 0 ? this.buffer.length + previousPointer : previousPointer;
    return this.buffer[previousPointer];
  }

  public void enqueue(GameEvent event, Initiator initiator) {
    Tuple<GameEvent, Initiator> tuple = new Tuple<>(event, initiator);
    this.queue.offer(tuple);
  }
  public void enqueue(Collection<GameEvent> events, Initiator initiator) {
    events.forEach(e -> this.enqueue(e, initiator));
  }
  public void register(SpecificationHandler<? extends GameEvent> handler) {
    this.handlers.put(handler.getGameEventType(), handler);
  }


  /**
   * Main processing loop that iterates
   * over the enqueued game events
   */
  public void start() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    // init buffer
    this.buffer = new Object[5];
    this.pointer = 0;

    while (this.queue.peek() != null) {
      Tuple<? extends GameEvent, Initiator> tuple = this.queue.poll();
      GameEvent event = tuple.x;
      Initiator initiator = tuple.y;

      // log execution of event
      log.info("executing event '%s' initiated by '%s'",
          event.getClass().getSimpleName(),
          initiator.getClass().getSimpleName());

      // get method reference for handling event
      Method handle = SpecificationHandler
          .class.getDeclaredMethod("handle", GameEvent.class, Initiator.class, Executor.class);

      // invoke handling of event
      SpecificationHandler<?> instance = this.handlers.get(event.getClass());
      if (instance == null) throw new RuntimeException("handling of this event is not supported");
      handle.invoke(instance, tuple.x, initiator, this);

      // log buffer usage
      log.info("buffer: " + Arrays.toString(buffer));
      log.info("buffer pointer: " + this.pointer);
    }

  }
}
