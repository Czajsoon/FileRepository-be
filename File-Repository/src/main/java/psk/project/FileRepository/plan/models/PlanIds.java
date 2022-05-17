package psk.project.FileRepository.plan.models;

public enum PlanIds {
    NORMAL, PREMIUM, PREMIUMPLUS;

    public Long getValue() {
        return (long) ordinal() + 1;
    }
}
