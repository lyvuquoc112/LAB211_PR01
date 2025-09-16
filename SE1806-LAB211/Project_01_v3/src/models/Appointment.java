package com.smartclinic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    // Liên kết Many-to-One tới Doctor
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    // Liên kết Many-to-One tới Patient
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // Thời gian hẹn: bắt buộc & phải ở tương lai
    @NotNull(message = "Appointment time is required")
    @Future(message = "Appointment time must be in the future")
    @Column(name = "appointment_time", nullable = false)
    private LocalDateTime appointmentTime;

    // Trạng thái hẹn (tùy biến)
    @Column(length = 30, nullable = false)
    private String status = "SCHEDULED";

    // Ghi chú (nếu cần)
    @Column(length = 500)
    private String notes;

    public Appointment() {}

    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, String status, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        if (status != null) this.status = status;
        this.notes = notes;
    }

    // Getters & Setters
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
