package algorithm;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author fengcaiwen
 * @since 5/29/2019
 * <p>
 * 电梯运行算法，
 * 电梯调度算法
 */
public class ElevatorTest {

    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = new HashSet<>();
        set.add(3);
        set.add(7);
        set.add(10);
        set.add(5);
        set.add(1);
        Elevator elevator = new Elevator("a", 0, 3, set);
        new ElevatorTest().run(elevator);

    }

    public void run(Elevator elevator) throws InterruptedException {
        int target = getNextMiddleTarget(elevator);
        int direction = getDirection(elevator);

        //1, running
        if (direction != 0) {
            System.out.println("running to floor: " + elevator.current);
            Thread.sleep(500);
            elevator.current += direction == 1 ? 1 : direction == -1 ? -1 : 0;
            run(elevator);

        } else {
            // 2,arrived
            elevator.targets.remove(elevator.target);
            System.out.println("arrived floor:" + elevator.target);
            elevator.target = getNextMiddleTarget(elevator);
            Thread.sleep(1000);
            run(elevator);
        }
    }

    private int getNextMiddleTarget(Elevator elevator) {
        if (elevator.targets.size() == 0) {
            return Integer.MIN_VALUE;
        }

        int difference = elevator.current - elevator.target;

        List<Integer> result = new ArrayList<>();
        //1, down
        if (difference > 0) {
            for (int i = elevator.current; i > elevator.target; i--) {
                if (elevator.targets.contains(i)) {
                    result.add(i);
                }
            }
            Collections.sort(result);
            return result.get(result.size() - 1);
        } else if (difference < 0) {
            // 2，up
            for (int i = elevator.current; i < elevator.target; i++) {
                if (elevator.targets.contains(i)) {
                    result.add(i);
                }
            }

            Collections.sort(result);
            return result.get(0);
        } else {
            return (int) elevator.getTargets().toArray()[0];
        }

    }

    public int getDirection(Elevator elevator) {
        int i = elevator.current - elevator.target;
        return Integer.compare(0, i);
    }

    public int byElevator(List<Elevator> elevatorList, int target) {
        return -1;
    }

    public int getNextTarget(int current, Queue<Integer>[] targets) {
        return -1;
    }

    public int getNextTarget(int current, Queue<Integer> target) {
        return -1;
    }


    public static class Elevator {
        private String name;
        private int current;
        private int target;
        private Set<Integer> targets;

        public Elevator() {
        }

        public Elevator(String name, int current, int target, Set<Integer> targets) {
            this.name = name;
            this.current = current;
            this.target = target;
            this.targets = targets;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }

        public Set<Integer> getTargets() {
            return targets;
        }

        public void setTargets(Set<Integer> targets) {
            this.targets = targets;
        }
    }
}
