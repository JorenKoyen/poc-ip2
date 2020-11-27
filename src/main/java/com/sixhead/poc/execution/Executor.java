package com.sixhead.poc.execution;

import com.sixhead.poc.Tuple;
import com.sixhead.poc.util.Logger;

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

  protected void setBufferState(Object o) {
    this.buffer[this.pointer] = o;
    this.pointer = (this.pointer + 1) % this.buffer.length;
  }

  protected Object getLastState() {
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
  public void start() {
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


      // extract handler from registered handlers
      @SuppressWarnings("unchecked") // suppress warning for unchecked casting
      SpecificationHandler<GameEvent> handler = (SpecificationHandler<GameEvent>) this.handlers.get(event.getClass());
      if (handler == null) throw new RuntimeException("handling of this specification is not supported");

      // start handling of event
      handler.handle(tuple.x, initiator, this);

      // log buffer usage
      log.info("buffer: " + Arrays.toString(buffer));
      log.info("buffer pointer: " + this.pointer);
    }

  }
}
