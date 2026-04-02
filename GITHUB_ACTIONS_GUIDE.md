# 🚀 GitHub Actions Compilation Guide

## Что это?
GitHub Actions - **бесплатный облачный компилятор** для проектов на GitHub. Он автоматически компилирует ваш мод при каждом обновлении.

---

## ✅ Шаг 1: Создать GitHub аккаунт

1. Перейдите на https://github.com/signup
2. Заполните форму (email, пароль)
3. Нажмите "Create account"
4. Подтвердите email

---

## ✅ Шаг 2: Создать новый репозиторий

1. Перейдите на https://github.com/new
2. Назовите репозиторий: `elitras-swap-mod`
3. Описание: `Forge Mod for Minecraft 1.21.11 - Auto-swap elytra and armor`
4. Выберите **Public** (чтобы скачивать артифакты)
5. Нажмите "Create repository"

---

## ✅ Шаг 3: Загрузить файлы проекта

### Способ A: Через VS Code (Рекомендуется)

```bash
# Terminal в VS Code:
cd e:\elitraswappvp

# Инициализировать Git репозиторий
git config --global user.email "your-email@gmail.com"
git config --global user.name "Your Name"
git init
git add .
git commit -m "Initial commit - Forge mod source"

# Добавить удаленный репозиторий (замените USERNAME)
git remote add origin https://github.com/USERNAME/elitras-swap-mod.git

# Загрузить на GitHub
git branch -M main
git push -u origin main
```

### Способ B: Через веб-интерфейс GitHub

1. На странице репозитория нажмите "Add file" → "Upload files"
2. Перетащите файлы из `e:\elitraswappvp`
3. Нажмите "Commit changes"

---

## ✅ Шаг 4: GitHub Actions запустится автоматически

1. Перейдите в репозиторий
2. Откройте вкладку **"Actions"**
3. Увидите процесс компиляции
4. ⏳ Ждите ~10 минут

---

## ✅ Шаг 5: Скачать скомпилированный JAR

1. После успешной компиляции откройте последнюю Build
2. Нажмите **"Artifacts"** → **"forge-mod"**
3. Скачайте `elitras-swap-1.0.0.jar`

---

## 📦 Установить мод

1. Скопируйте загруженный JAR в:
   ```
   %APPDATA%\.minecraft\mods\
   ```

2. Запустите Minecraft с Forge 1.21.11
3. Мод должен появиться в списке модов и загрузиться! ✅

---

## 🔧 Если что-то пошло не так

**Ошибка в компиляции?**
1. Откройте вкладку "Actions"
2. Кликните на неудавшуюся сборку
3. Найдите раздел "Build with Gradle"
4. Посмотрите полный лог ошибки

**Нужно переработать код?**
1. Отредактируйте файлы локально
2. Запустите:
   ```bash
   git add .
   git commit -m "Fix: description of fix"
   git push
   ```
3. GitHub Actions автоматически перекомпилирует!

---

## 🎯 Итоговая схема

```
e:\elitraswappvp (локальная папка)
        ↓
    git push
        ↓
GitHub Repository
        ↓
GitHub Actions (компиляция)
        ↓
✅ elitras-swap-1.0.0.jar
        ↓
Скачать и установить в mods/
```

---

## ⚡ Быстрая сборка (если Git не установлен)

**Просто используйте GitHub веб-интерфейс:**

1. Создайте репозиторий
2. Нажмите "Add file" → "Upload files"
3. Перетащите папку `e:\elitraswappvp`
4. GitHub Actions автоматически начнет сборку!

---

**Дата**: 2 апреля 2026  
**Время компиляции**: ~10 минут первый раз  
**Статус**: 🟢 ГОТОВО К ИСПОЛЬЗОВАНИЮ
