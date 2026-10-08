INSERT INTO patient (name, gender, birth_date, email, blood_group)
VALUES
    ('Rohan Sharma', 'Male', '1998-05-21', 'rohan.sharma@gmail.com', 'B_POSITIVE'),
    ('Priya Verma', 'Female', '2001-08-14', 'priya.verma@gmail.com', 'O_POSITIVE'),
    ('Aman Gupta', 'Male', '1995-12-02', 'aman.gupta@yahoo.com', 'A_NEGATIVE'),
    ('Neha Singh', 'Female', '1999-03-10', 'neha.singh@hotmail.com', 'AB_POSITIVE'),
    ('Arjun Mehta', 'Male', '2000-06-18', 'arjun.mehta@gmail.com', 'A_POSITIVE'),
    ('Vikram Rao', 'Male', '1993-02-05', 'vikram.rao@gmail.com', 'B_POSITIVE'),
    ('Radha Nair', 'Female', '1997-07-2', 'radha.nair@gmail.com', 'B_POSITIVE');
INSERT INTO doctor (name, specialization, email)
VALUES
('Dr. Rajesh Kumar', 'Cardiology', 'rajesh.kumar@hospital.com'),
('Dr. Neha Sharma', 'Neurology', 'neha.sharma@hospital.com'),
('Dr. Ankit Verma', 'Orthopedics', 'ankit.verma@hospital.com'),
('Dr. Priya Desai', 'Pediatrics', 'priya.desai@hospital.com'),
('Dr. Arjun Mehta', 'Dermatology', 'arjun.mehta@hospital.com'),
('Dr. Meera Joshi', 'Gynecology', 'meera.joshi@hospital.com'),
('Dr. Rohan Patel', 'Ophthalmology', 'rohan.patel@hospital.com'),
('Dr. Sneha Nair', 'Psychiatry', 'sneha.nair@hospital.com'),
('Dr. Kavita Rao', 'ENT', 'kavita.rao@hospital.com');


INSERT INTO appointment (appointment_time, reason, doctor_id, patient_id)
VALUES
('2025-11-01 09:00:00', 'Fever and Cold', 1, 2),
('2025-11-01 10:30:00', 'Routine Checkup', 2, 4),
('2025-11-01 11:45:00', 'Blood Pressure Follow-up', 3, 1),
('2025-11-02 08:15:00', 'Diabetes Consultation', 1, 3),
('2025-11-02 09:45:00', 'Chest Pain', 4, 5),
('2025-11-02 11:00:00', 'Allergy Treatment', 2, 6),
('2025-11-03 10:00:00', 'Post-surgery Follow-up', 3, 7);

--
---- 1️⃣ First insert into insurance
--INSERT INTO insurance (policy_number, provider, valid_until, created_at)
--VALUES
--('POL123', 'LIC', '2030-01-01', NOW()),
--('POL124', 'Star Health', '2031-06-15', NOW());
--
---- 2️⃣ Then insert patients referencing the insurance IDs
--INSERT INTO patient (name, gender, birth_date, email, blood_group, patient_insurance_id)
--VALUES
--('Rohan Sharma', 'Male', '1998-05-21', 'rohan.sharma@gmail.com', 'B_POSITIVE', 1),
--('Priya Verma', 'Female', '2001-08-14', 'priya.verma@gmail.com', 'O_POSITIVE', 2);
