package cleancode.studycafe.tobe.pass;

import java.util.Set;

public enum StudyCafePassType {

    HOURLY("시간 단위 이용권"),
    WEEKLY("주 단위 이용권"),
    FIXED("1인 고정석");

    public static final Set<StudyCafePassType> LOCKER_TYPES = Set.of(FIXED);

    private final String description;

    StudyCafePassType(String description) {
        this.description = description;
    }

    public boolean isLockerType() {
        return LOCKER_TYPES.contains(this);
    }

    public boolean isHourlyType() {
        return this == HOURLY;
    }

    public boolean isWeeklyType() {
        return this == WEEKLY;
    }

    public boolean isFixedType() {
        return this == FIXED;
    }
}
