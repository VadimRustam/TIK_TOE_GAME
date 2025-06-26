# 📊 Smart Clinic – Проектирование базы данных (schema-design.md)

## 1. Архитектура базы данных

База данных проекта **Smart Clinic** предназначена для хранения информации о пользователях (пациенты, врачи, администраторы), приёмах, медицинских записях и специализациях врачей.  
Структура базы данных нормализована и построена с целью обеспечения целостности данных, удобства поиска, обновления и масштабируемости системы.

---

## 2. Таблицы и их назначение

| Таблица            | Назначение |
|--------------------|-----------|
| `users`            | Общая информация о пользователях: имя, email, логин и роль |
| `roles`            | Хранение возможных ролей: администратор, врач, пациент |
| `patients`         | Расширенная информация о пациентах (например, дата рождения, номер телефона) |
| `doctors`          | Расширенная информация о врачах и ссылка на специализацию |
| `specialties`      | Список специализаций врачей (например, терапевт, хирург) |
| `appointments`     | Информация о приёмах: дата, время, пациент, врач |
| `medical_records`  | Медицинские записи пациентов, включая диагнозы и назначения |

---

## 3. Связи между таблицами

- Один **пользователь (`users`)** связан с **одной ролью (`roles`)** → (многие к одному)
- Один **врач (`doctors`)** связан с **одной специализацией (`specialties`)** → (многие к одному)
- Один **пациент (`patients`)** может иметь **много приёмов (`appointments`)** → (один ко многим)
- Один **врач (`doctors`)** может иметь **много приёмов (`appointments`)** → (один ко многим)
- Один **пациент (`patients`)** может иметь **несколько медицинских записей (`medical_records`)** → (один ко многим)

---

## 4. Пример SQL-схем таблиц

```sql
CREATE TABLE roles (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE patients (
    patient_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    date_of_birth DATE,
    phone VARCHAR(20),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE specialties (
    specialty_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    specialty_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (specialty_id) REFERENCES specialties(specialty_id)
);

CREATE TABLE appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_id INT NOT NULL,
    patient_id INT NOT NULL,
    appointment_time DATETIME NOT NULL,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id),
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

CREATE TABLE medical_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    diagnosis TEXT,
    treatment TEXT,
    record_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);
