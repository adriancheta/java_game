package main;

import entity.PlayerInput;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class GamepadHandler implements PlayerInput {

    private Controller gamepad;

    private boolean upPressed, downPressed,  leftPressed, rightPressed;

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

    public GamepadHandler() {

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for (Controller c : controllers) {
            if (c.getType() == Controller.Type.GAMEPAD || c.getType() == Controller.Type.STICK) {
                gamepad = c;
                break;
            }
        }
    }

    public void update() {

        if (gamepad == null) {
            upPressed = downPressed = leftPressed = rightPressed = false;
            return;
        }

        if (!gamepad.poll()) {
            upPressed = downPressed = leftPressed = rightPressed = false;
            gamepad = null;
            return;
        }

        upPressed = downPressed = leftPressed = rightPressed = false;

        Component[] components = gamepad.getComponents();
        float deadZone = 0.2f; // from where do we count moving the joystick as an event

        for (Component comp : components) {

            Component.Identifier id = comp.getIdentifier();
            float value = comp.getPollData();

            if (id == Component.Identifier.Axis.X) {
                if (value < -deadZone) {
                    leftPressed = true;
                }
                else if (value > deadZone) {
                    rightPressed = true;
                }
            }

            if (id == Component.Identifier.Axis.Y) {
                if (value < -deadZone) {
                    upPressed = true;
                }
                else if (value > deadZone) {
                    downPressed = true;
                }
            }
        }
    }
}


