
import tkinter as tk

buffer1 = 0
buffer2 = 0

def create_widget(parent, widget_type, **options):
    return widget_type(parent, **options)

window = create_widget(None, tk.Tk)
window.title("Calculadora")

frame = create_widget(window, tk.Frame, bg='black', bd=2, height=1000, width=1000)
frame.pack(padx=20, pady=5)

label = create_widget(frame, tk.Label, text="0", width=27, height=2, font=50, anchor="w", padx=10)
label.pack()

button_frame = create_widget(window, tk.Frame)
button_frame.pack(padx=10, pady=10)

def click_button(texto_boton):
    texto_actual = label.cget("text")
    nuevo_texto = texto_actual + texto_boton
    if len(nuevo_texto) < 28:
        label.configure(text=nuevo_texto)

buttons = [
    ("7", 0, 0), ("8", 1, 0), ("9", 2, 0),
    ("4", 0, 1), ("5", 1, 1), ("6", 2, 1),
    ("1", 0, 2), ("2", 1, 2), ("3", 2, 2)]

for text, col, row in buttons:
    button = create_widget(button_frame, tk.Button, text=text, font=50, command=lambda t=text: click_button(t), width=5, height=2)
    button.grid(column=col, row=row, sticky="nsew")

def click_button_cero():
    texto_actual = label.cget("text")
    if texto_actual != "0":
        nuevo_texto = texto_actual + "0"
        if len(nuevo_texto) < 22:
            label.configure(text=nuevo_texto)

boton_cero = create_widget(button_frame, tk.Button ,text="0", font=50, command=click_button_cero, width=5, height=2)
boton_cero.grid(column=1, row=3, sticky="nsew")

def click_button_coma():
    texto_actual = label.cget("text")
    nuevo_texto = texto_actual + ","
    if len(nuevo_texto) < 22 and "," not in texto_actual:
        label.configure(text=nuevo_texto)

boton_coma = create_widget(button_frame, tk.Button, text=",", font=50, command=click_button_coma, width=5, height=2)
boton_coma.grid(column=2, row=3, sticky="nsew")

def click_button_div():
    return

boton_div = create_widget(button_frame, tk.Button ,text="÷", font=50, command=click_button_div, width=5, height=2)
boton_div.grid(column=3, row=0, sticky="nsew")

def click_button_mult():
    return

boton_mult = create_widget(button_frame, tk.Button ,text="×", font=50, command=click_button_mult, width=5, height=2)
boton_mult.grid(column=3, row=1, sticky="nsew")

def click_button_resta():
    return

boton_resta = create_widget(button_frame, tk.Button ,text="-", font=50, command=click_button_resta, width=5, height=2)
boton_resta.grid(column=3, row=2, sticky="nsew")

def click_button_suma():
    return

boton_suma = create_widget(button_frame, tk.Button ,text="+", font=50, command=click_button_suma, width=5, height=2)
boton_suma.grid(column=3, row=3, sticky="nsew")

def click_button_igual():
    return

boton_igual = create_widget(button_frame, tk.Button, text="=", font=50, command=click_button_igual, width=5, height=8)
boton_igual.grid(column=4, row=1, rowspan=3, sticky="nsew")

def click_button_borrar_todo():
    label.configure(text="0")
    #Añadir tambien que borre todas las variables

boton_borrar_todo = create_widget(button_frame, tk.Button, text="C", font=50, command=click_button_borrar_todo, width=5, height=2)
boton_borrar_todo.grid(column=0, row=3, sticky="nsew")

def click_button_borrar_uno():
    texto_actual = label.cget("text")
    nuevo_texto = texto_actual[:-1]
    if nuevo_texto == "":
        nuevo_texto = "0"
    label.configure(text=nuevo_texto)

boton_borrar_uno = create_widget(button_frame, tk.Button, text="←", font=50, command=click_button_borrar_uno, width=5, height=2)
boton_borrar_uno.grid(column=4, row=0, sticky="nsew")


window.mainloop()