@echo off
echo Compiling Java files...
javac Item.java InventoryGUI.java

if %errorlevel% neq 0 (
    echo Compilation failed. Press any key to exit.
    pause >nul
    exit /b
)

echo Running Inventory GUI...
java InventoryGUI

echo.
pause
