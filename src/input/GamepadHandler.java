package input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class GamepadHandler implements PlayerInput {

    private Controller gamepad;

    private boolean upPressed = false, downPressed = false;
    private boolean leftPressed = false, rightPressed = false;
    private boolean attackPressed = false, dashPressed = false;
    private boolean alreadyPrintedNoGamepad = false;


    @Override
    public boolean isUpPressed() {
        return upPressed;
    }

    @Override
    public boolean isDownPressed() {
        return downPressed;
    }

    @Override
    public boolean isLeftPressed() {
        return leftPressed;
    }

    @Override
    public boolean isRightPressed() {
        return rightPressed;
    }

    @Override
    public boolean isAttackPressed() {
        return attackPressed;
    }

    @Override
    public boolean isDashPressed() {
        return dashPressed;
    }

    public GamepadHandler() {

        printDetectedControllers();

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for (Controller c : controllers) {
            if (c.getType() == Controller.Type.GAMEPAD || c.getType() == Controller.Type.STICK) {
                gamepad = c;
                break;
            }
        }
    }

    public void update() {

        upPressed = downPressed = leftPressed = rightPressed = false;
        dashPressed = attackPressed = false;

        if (gamepad == null) {
            scanForController();
            if (gamepad == null) {
                return;
            }
        }

        if (!gamepad.poll()) {
            gamepad = null;
            return;
        }

        float deadZone = 0.2f; // From where do we count moving the joystick as an event
        float pressZone = 0.5f; // From where do we count pressing the button as pressed

        for (Component comp : gamepad.getComponents()) {

            Component.Identifier id = comp.getIdentifier();
            float value = comp.getPollData();

            if (id == Component.Identifier.Button._1) {
                if (value >= pressZone) {
                    dashPressed = true;
                }
            }

            if (id == Component.Identifier.Button._2) {
                if (value >= pressZone) {
                    attackPressed = true;
                }
            }

            if (id == Component.Identifier.Axis.X) {
                if (value < -deadZone) {
                    leftPressed = true;
                } else if (value > deadZone) {
                    rightPressed = true;
                }
            }

            if (id == Component.Identifier.Axis.Y) {
                if (value < -deadZone) {
                    upPressed = true;
                } else if (value > deadZone) {
                    downPressed = true;
                }
            }
        }
    }

    private void scanForController() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for (Controller c : controllers) {
            if (c.getType() == Controller.Type.GAMEPAD || c.getType() == Controller.Type.STICK || c.getType() == Controller.Type.UNKNOWN) {
                // Prefer controllers that actually have X/Y axes
                alreadyPrintedNoGamepad = false;
                boolean hasX = false, hasY = false;
                for (Component comp : c.getComponents()) {
                    if (comp.getIdentifier() == Component.Identifier.Axis.X) hasX = true;
                    if (comp.getIdentifier() == Component.Identifier.Axis.Y) hasY = true;
                }
                if (hasX && hasY) {
                    gamepad = c;
                    System.out.println("Gamepad selected: " + c.getName() + " (" + c.getType() + ")");
                    return;
                }
            }
        }

        if (!alreadyPrintedNoGamepad) {
            System.out.println("No gamepad detected by JInput.");
            alreadyPrintedNoGamepad = true;
        }
    }

    private void printDetectedControllers() {

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        System.out.println("Detected controllers: " + controllers.length);

        for (Controller c : controllers) {
            System.out.println("Controller: " + c.getName() + " | Type: " + c.getType());

            Component[] components = c.getComponents();
            for (Component comp : components) {
                System.out.println("  - " + comp.getName() + " | " + comp.getIdentifier());
            }
        }
    }
}