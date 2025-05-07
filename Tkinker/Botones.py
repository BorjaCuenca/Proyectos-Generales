
from tkinter import *

def createWidget(parent, widget_type, **options):
    return widget_type(parent, **options)

window = createWidget(None, Tk)
window.title("Botones")

frame = createWidget(window, Frame, bg='black', bd=2, height=100, width=200)
frame.pack(padx=20, pady=20)

label = createWidget(frame, Label, text="Texto de prueba")
label.pack()

button_frame = createWidget(window, Frame)
button_frame.pack(padx=10, pady=10)

def clickButton(textoBoton):
    label.configure(text="Texto " + textoBoton)

buttons_info = [("Boton1", "red"), ("Boton2", "brown"), ("Boton3", "blue"),
                ("Boton4", "green"), ("Boton5", "green"), ("Boton6", "green")]

for text, fg in buttons_info:
    button = createWidget(button_frame,Button ,text=text, fg=fg, command=lambda t=text: clickButton(t))
    button.pack(side=LEFT)

window.mainloop()