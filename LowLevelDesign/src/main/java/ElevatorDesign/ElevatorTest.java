/**
 * https://www.youtube.com/watch?v=FptCbX7fRHw
 * https://www.javastructures.com/design/elevator
 */

package ElevatorDesign;

import java.util.*;

enum Direction {
    UP, DOWN;
}

enum State {
    IDLE, MOVING, STOPPED;
}

class ExternalRequest {
    int sourceFloor;
    Direction directionToGo;

    ExternalRequest(int sourceFloor, Direction direction) {
        this.sourceFloor = sourceFloor;
        this.directionToGo = direction;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public Direction getDirectionToGo() {
        return directionToGo;
    }

    @Override
    public String toString() {
        return "ExternalRequest {" +
                "sourceFloor=" + sourceFloor +
                ", direction=" + directionToGo +
                '}';
    }
}

class InternalRequest {
    int destinationFloor;

    InternalRequest(int dest) {
        this.destinationFloor = dest;
    }

    public int getDestinationFloor() {
        return this.destinationFloor;
    }

    @Override
    public String toString() {
        return "InternalRequest {" +
                "destinationFloor=" + destinationFloor +
                '}';
    }
}

class Request implements Comparable<Request> {
    ExternalRequest externalRequest;
    InternalRequest internalRequest;

    public Request(ExternalRequest externalRequest, InternalRequest internalRequest) {
        this.externalRequest = externalRequest;
        this.internalRequest = internalRequest;
    }

    public ExternalRequest getExternalRequest() {
        return externalRequest;
    }

    public InternalRequest getInternalRequest() {
        return internalRequest;
    }

    @Override
    public int compareTo(Request obj) {
        Integer dest1 = this.internalRequest.destinationFloor;
        Integer dest2 = obj.internalRequest.destinationFloor;
        return dest1.compareTo(dest2);
    }

    @Override
    public String toString() {
        return "Request {" +
                "externalRequest=" + externalRequest +
                ", internalRequest=" + internalRequest +
                '}';
    }
}

class Elevator {
    private Direction currentDirection = Direction.UP;
    private State currentState = State.IDLE;
    private int currentFloor = 0;

    private TreeSet<Request> currentJobs = new TreeSet<>();
    private TreeSet<Request> upPendingJobs = new TreeSet<>();
    private TreeSet<Request> downPendingJobs = new TreeSet<>();

    public void startElevator() {
        while(true) {
            if(checkIfCurrentJob()) {
                if(currentDirection == Direction.UP) {
                    Request request = currentJobs.pollFirst();
                    processUpRequest(request);
                    if(!checkIfCurrentJob()) {
                        addPendingDownJobsToCurrentJobs();
                    }
                }

                if(currentDirection == Direction.DOWN) {
                    Request request = currentJobs.pollLast();
                    processDownRequest(request);
                    if(!checkIfCurrentJob()) {
                        addPendingUpJobsToCurrentJobs();
                    }
                }
            }
        }
    }

    public void processDownRequest(Request request) {
        // Call the lift to the floor if not present on current floor
        int startFloor = currentFloor;
        if(startFloor < request.getExternalRequest().getSourceFloor()) {
            for(int i=startFloor; i<=request.getExternalRequest().getSourceFloor(); i++) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lift has reached to the Floor: " + i);
                currentFloor = i;
            }
        }
        startFloor = currentFloor;
        System.out.println("Lift has reached to the source Floor, " +
                "Opening the door now: " + currentFloor);
        System.out.println("Going towards Destination Floor: " +
                request.getInternalRequest().getDestinationFloor());

        for(int i=startFloor; i>=request.getInternalRequest().getDestinationFloor(); i--) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lift has reached to the Floor: " + i);
            currentFloor = i;
            if(checkIfNewJobCanBeProcessed(request)) {
                // First we will process that request then we will process the current request
                break;
            }
        }
    }

    public void processUpRequest(Request request) {
        // Call the lift to the floor if not present on current floor
        int startFloor = this.currentFloor;
        if(startFloor < request.getExternalRequest().getSourceFloor()) {
            for(int i=startFloor; i<=request.getExternalRequest().getSourceFloor(); i++) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lift has reached to the Floor: " + i);
                this.currentFloor = i;
            }
        }
        startFloor = this.currentFloor;
        System.out.println("Lift has reached to the source Floor, " +
                "Opening the door now: " + this.currentFloor);
        System.out.println("Going towards Destination Floor: " +
                request.getInternalRequest().getDestinationFloor());
        for(int i=startFloor; i<=request.getInternalRequest().getDestinationFloor(); i++) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Lift has reached to the Floor: " + i);
            this.currentFloor = i;
            if(checkIfNewJobCanBeProcessed(request)) {
                // First we will process that request then we will process the current request
                break;
            }
        }
    }

    private boolean checkIfNewJobCanBeProcessed(Request currentRequest) {
        if(checkIfCurrentJob()) {
            if(currentDirection == Direction.UP) {
                Request request = currentJobs.pollFirst();
                if(request.getInternalRequest().getDestinationFloor() <
                        currentRequest.getInternalRequest().getDestinationFloor()) {
                    currentJobs.add(request);
                    currentJobs.add(currentRequest);
                    return true;
                }
                currentJobs.add(request);
            }

            if(currentDirection == Direction.DOWN) {
                Request request = currentJobs.pollFirst();
                if(request.getInternalRequest().getDestinationFloor() >
                        currentRequest.getInternalRequest().getDestinationFloor()) {
                    currentJobs.add(request);
                    currentJobs.add(currentRequest);
                    return true;
                }
                currentJobs.add(request);
            }
        }
        return false;
    }

    private void addPendingDownJobsToCurrentJobs() {
        if(!downPendingJobs.isEmpty()) {
            currentJobs = downPendingJobs;
            currentDirection = Direction.DOWN;
        }
        else {
            currentState = State.IDLE;
        }
    }

    private void addPendingUpJobsToCurrentJobs() {
        if(!upPendingJobs.isEmpty()) {
            currentJobs = upPendingJobs;
            currentDirection = Direction.UP;
        }
        else {
            currentState = State.IDLE;
        }
    }

    public boolean checkIfCurrentJob() {
        return currentJobs.isEmpty();
    }

    public void addJob(Request request) {
        if(currentState == State.IDLE) {
            if(currentFloor == request.getExternalRequest().getSourceFloor()) {
                System.out.println("Added current queue job -- lift state is - " + currentState + " location is - "
                        + currentFloor + " to move to floor - " + request.getInternalRequest().getDestinationFloor());
            }
            currentState = State.MOVING;
            currentDirection = request.externalRequest.getDirectionToGo();
            currentJobs.add(request);
        }
        else if(currentState == State.MOVING) {
            if(currentDirection != request.getExternalRequest().getDirectionToGo()) {
                addToPendingJobs(request);
            }
            else if(currentDirection == request.externalRequest.getDirectionToGo()) {
                if(currentDirection == Direction.UP && request.getInternalRequest().getDestinationFloor() < currentFloor) {
                    addToPendingJobs(request);
                }
                else if(currentDirection == Direction.DOWN && request.getInternalRequest().getDestinationFloor() > currentFloor) {
                    addToPendingJobs(request);
                }
                else {
                    currentJobs.add(request);
                }
            }
        }
    }

    public void addToPendingJobs(Request request) {
        if(request.getExternalRequest().getDirectionToGo() == Direction.UP) {
            System.out.println("Add to Up Pending Jobs");
            upPendingJobs.add(request);
        }
        else {
            System.out.println("Add to Down Pending Jobs");
            downPendingJobs.add(request);
        }
    }
}

class ProcessJobWorker implements Runnable {
    private final Elevator elevator;

    public ProcessJobWorker(Elevator elevator) {
        this.elevator = elevator;
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevator.startElevator();
    }
}

class AddJobWorker implements Runnable {
    private final Elevator elevator;
    private final Request request;

    public AddJobWorker(Elevator elevator, Request request) {
        this.elevator = elevator;
        this.request = request;
    }

    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevator.addJob(request);
    }
}

public class ElevatorTest {
    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        Thread t1 = new Thread(new ProcessJobWorker(elevator));
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ExternalRequest er = new ExternalRequest(0, Direction.UP);
        InternalRequest ir = new InternalRequest(5);
        Request request1 = new Request(er, ir);

        new Thread(new AddJobWorker(elevator, request1)).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
