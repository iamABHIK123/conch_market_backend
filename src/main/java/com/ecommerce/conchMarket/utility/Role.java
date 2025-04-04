package com.ecommerce.conchMarket.utility;

import java.sql.Timestamp;
import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String role;
	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

	@Column(name = "modified_at", nullable = true)
	private Timestamp modifiedAt;
	
//	@ManyToMany(mappedBy = "roles")
//    private Set<User> users = new HashSet<>();
	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName="id")
	private User user;
	
	@PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
        modifiedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedAt = new Timestamp(System.currentTimeMillis());
    }


}
