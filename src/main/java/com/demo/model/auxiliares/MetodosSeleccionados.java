package com.demo.model.auxiliares;

import com.demo.model.Method;

public class MetodosSeleccionados {
    private boolean isSelected=false;
    private Method method;

    public boolean isSelected() {
        return isSelected;
    }

    public boolean getSelected() {
        return isSelected;
    };


    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public MetodosSeleccionados(boolean isSelected, Method method) {
        this.isSelected = isSelected;
        this.method = method;
    }

    public MetodosSeleccionados() {
    }

    @Override
    public String toString() {
        return "MetodoMuestraPojo{" +
                "isSelected=" + isSelected +
                ", method=" + method +
                '}';
    }
}
