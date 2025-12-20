package input;

public class CombinedInput implements PlayerInput {

    private final PlayerInput a;
    private final PlayerInput b;

    public CombinedInput (PlayerInput a, PlayerInput b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean isUpPressed() {
        return a.isUpPressed() || b.isUpPressed();
    }

    @Override
    public boolean isDownPressed() {
        return a.isDownPressed() || b.isDownPressed();
    }

    @Override
    public boolean isLeftPressed() {
        return a.isLeftPressed() || b.isLeftPressed();
    }

    @Override
    public boolean isRightPressed() {
        return a.isRightPressed() || b.isRightPressed();
    }

    @Override
    public boolean isAttackPressed() {
        return a.isAttackPressed() || b.isAttackPressed();
    }

    @Override
    public boolean isDashPressed() {
        return a.isDashPressed() || b.isDashPressed();
    }
}