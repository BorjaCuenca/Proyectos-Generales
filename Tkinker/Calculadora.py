
from tkinter import *

def create_widget(parent, widget_type, **options):
    return widget_type(parent, **options)

window = create_widget(None, Tk)
window.title("Calculadora")

frame = create_widget(window, Frame, bg='black', bd=2, height=1000, width=1000)
frame.pack(padx=20)

label = create_widget(frame, Label, text="", width=21, height=2, font=50, anchor=W, padx=10)
label.pack()

button_frame = create_widget(window, Frame)
button_frame.pack(padx=10, pady=10)

def click_button(texto_boton):
    texto_actual = label.cget("text")
    nuevo_texto = texto_actual + texto_boton
    if len(nuevo_texto) < 22:
        label.configure(text=nuevo_texto)

buttons = [
    ("7", 0, 0), ("8", 1, 0), ("9", 2, 0),
    ("4", 0, 1), ("5", 1, 1), ("6", 2, 1),
    ("1", 0, 2), ("2", 1, 2), ("3", 2, 2)]

for text, col, row in buttons:
    button = create_widget(button_frame, Button, text=text, font=50, command=lambda t=text: click_button(t), width=5, height=2)
    button.grid(column=col, row=row)

def click_button_cero():
    texto_actual = label.cget("text")
    if texto_actual != "":
        nuevo_texto = texto_actual + "0"
        if len(nuevo_texto) < 22:
            label.configure(text=nuevo_texto)

boton_cero = create_widget(button_frame, Button ,text="0", font=50, command=click_button_cero, width=5, height=2)
boton_cero.grid(column=1, row=3)

def click_button_mult():
    return

boton_mult = create_widget(button_frame, Button ,text="×", font=50, command=click_button_mult, width=5, height=2)
boton_mult.grid(column=3, row=0)

def click_button_resta():
    return

boton_resta = create_widget(button_frame, Button ,text="-", font=50, command=click_button_resta, width=5, height=2)
boton_resta.grid(column=3, row=1)

def click_button_suma():
    return

boton_suma = create_widget(button_frame, Button ,text="+", font=50, command=click_button_suma, width=5, height=2)
boton_suma.grid(column=3, row=2)

def click_button_igual():
    return

boton_igual = create_widget(button_frame, Button ,text="=", font=50, command=click_button_igual, width=5, height=2)
boton_igual.grid(column=3, row=3)

boton_relleno1 = create_widget(button_frame, Button, font=50, command=click_button_cero, width=5, height=2)
boton_relleno1.grid(column=0, row=3)
boton_relleno2 = create_widget(button_frame, Button, font=50, command=click_button_cero, width=5, height=2)
boton_relleno2.grid(column=2, row=3)


window.mainloop()