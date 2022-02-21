package com.company.dto;

import com.company.model.Habit;

public class ItemDto {
    private Long habit_id;

    private int sort;

    private boolean checked;

    private String icon;

    public ItemDto(Long habit_id, int sort, boolean checked, String icon) {
        this.habit_id = habit_id;
        this.sort = sort;
        this.checked = checked;
        this.icon = icon;
    }

    public boolean isChecked() { return checked; }

    public Long getHabit_id() { return habit_id; }

    public void setHabit(Long habit_id) { this.habit_id = habit_id; }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
