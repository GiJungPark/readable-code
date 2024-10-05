package cleancode.minesweeper.tobe.position;

import java.util.List;
import java.util.Objects;

public class RelativePosition {

    public static final List<RelativePosition> SURROUNDED_POSITIONS = List.of(
            RelativePosition.of(-1, -1),
            RelativePosition.of(-1, 0),
            RelativePosition.of(-1, 1),
            RelativePosition.of(0, -1),
            RelativePosition.of(0, 1),
            RelativePosition.of(1, -1),
            RelativePosition.of(1, 0),
            RelativePosition.of(1, -1)
    );

    private final int delaRow;
    private final int delaCol;

    private RelativePosition(int delaRow, int delaCol) {
        this.delaRow = delaRow;
        this.delaCol = delaCol;
    }

    public static RelativePosition of(int delaRow, int delaCol) {
        return new RelativePosition(delaRow, delaCol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelativePosition that = (RelativePosition) o;
        return delaRow == that.delaRow && delaCol == that.delaCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(delaRow, delaCol);
    }

    public int getDeltaRow() {
        return this.delaRow;
    }
    public int getDeltaCol() {
        return this.delaCol;
    }
}
