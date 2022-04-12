package com.github.annzem.banana.webapp.dto;

public class ItemsQueryDto {
    public ItemsQueryDto(Long user_id, Long habit_id, int sort, boolean checked, String icon) {
        this.user_id = user_id;
        this.habit_id = habit_id;
        this.sort = sort;
        this.checked = checked;
        this.icon = icon;
    }

    private Long id;

    private Long user_id;
    private Long habit_id;
    private int sort;
    private Boolean checked;
    private String icon;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHabit_id() {
        return habit_id;
    }

    public void setHabit_id(Long habit_id) {
        this.habit_id = habit_id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
