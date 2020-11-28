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
  private final Deque<Tuple<? extends GameEvent, Initiator>> queue;
  private final Object[] buffer;
  private int pointer;

  public Executor() {
    this.queue = new LinkedList<>();
    this.handlers = new HashMap<>();
    this.buffer = new Object[5];
    this.pointer = 0;
  }

  // -- buffer operations -----------------------
  public void setBufferState(Object o) {
    this.buffer[this.pointer] = o;
    this.pointer = (this.pointer + 1) % this.buffer.length;
  }
  public Object getLastState() {
    int previousPointer = this.pointer - 1;
    previousPointer = previousPointer < 0 ? this.buffer.length + previousPointer : previousPointer;
    return this.buffer[previousPointer];
  }

  // -- queue operations ------------------------
  public void enqueue(GameEvent event, Initiator initiator, boolean prioritise) {
    Tuple<GameEvent, Initiator> tuple = new Tuple<>(event, initiator);
    if (prioritise) {
      this.queue.offerFirst(tuple);
    } else {
      this.queue.offerLast(tuple);
    }

  }
  public void enqueue(GameEvent event, Initiator initiator) {
    this.enqueue(event, initiator, false);
  }
  public void enqueue(Collection<GameEvent> events, Initiator initiator) {
    events.forEach(e -> this.enqueue(e, initiator));
  }

  // -- handler operations ----------------------
  public void register(SpecificationHandler<? extends GameEvent> handler) {
    this.handlers.put(handler.getGameEventType(), handler);
  }


  /**
   * Main processing loop that iterates
   * over the enqueued game events
   */
  public void start() throws
      NoSuchMethodException,
      InvocationTargetException,
      IllegalAccessException
  {

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
      handle.invoke(instance, event, initiator, this);

      // log buffer usage
      log.debug("buffer: " + Arrays.toString(buffer));
      log.debug("buffer pointer: " + this.pointer);
    }

  }
}
