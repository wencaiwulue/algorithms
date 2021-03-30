package algorithm;

import java.util.*;

/**
 * @author fengcaiwen
 * @since 5/29/2019
 * <p>
 * 电梯运行算法，
 * 电梯调度算法
 */
public class ElevatorTest {

    public static void main(String[] args) throws InterruptedException {
        Set<Integer> set = new LinkedHashSet<>();
        set.add(3);
        set.add(2);
        set.add(7);
        set.add(10);
        set.add(5);
        set.add(1);
        Elevator elevator = new Elevator("a", 0, (Integer) set.toArray()[0], set);
        new Thread(() -> {
            try {
                new ElevatorTest().run(elevator);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                elevator.getTargets().add(i);
                System.out.println("we get num: " + i);
            }
        }).start();
    }

    public void run(Elevator elevator) throws InterruptedException {
        int target = elevator.target;
        int middleTarget = getNextMiddleTarget(elevator);
        if (target == Integer.MIN_VALUE) {
            System.out.println("done");
            return;
        }
        int direction = getDirection(elevator);
        boolean isRunning = middleTarget != elevator.current;

        //1, running
        if (isRunning) {
            System.out.println("running to floor: " + elevator.current + ", target is: " + target);
            Thread.sleep(500);
            elevator.current += direction == 1 ? 1 : direction == -1 ? -1 : 0;
            run(elevator);

        } else {
            // 2,arrived
            elevator.targets.remove(middleTarget);
            System.out.println("arrived floor:" + middleTarget);
            if (middleTarget != elevator.target) {

            } else {
                Object[] objects = elevator.getTargets().toArray();
                if (objects.length == 0) {
                    elevator.target = Integer.MIN_VALUE;
                } else {
                    elevator.target = (int) objects[0];
                }
            }
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
            boolean block = false;
            for (Integer next : elevator.getTargets()) {
                if (next >= elevator.target && next <= elevator.current) {
                    if (!block) {
                        result.add(next);
                    }
                } else {
                    block = true;
                }
            }
            Collections.sort(result);
            return result.get(result.size() - 1);
        } else if (difference < 0) {
            // 2，up
            for (Integer next : elevator.getTargets()) {
                if (next >= elevator.current && next <= elevator.target) {
                    result.add(next);
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
        private volatile Set<Integer> targets;

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Elevator elevator = (Elevator) o;
            return current == elevator.current &&
                    target == elevator.target &&
                    Objects.equals(name, elevator.name) &&
                    Objects.equals(targets, elevator.targets);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, current, target, targets);
        }
    }
}
