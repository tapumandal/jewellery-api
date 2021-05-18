package me.tapumandal.jewellery.domain.appnavigation;

import java.util.List;

public class MenuList {
    String menuName;
    String icon;
    boolean isGroup;
    boolean hasChildren;
    boolean open;
    List<MenuList> child;


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<MenuList> getChild() {
        return child;
    }

    public void setChild(List<MenuList> child) {
        this.child = child;
    }
}
