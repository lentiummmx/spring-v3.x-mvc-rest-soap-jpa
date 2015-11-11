/**
 * 
 */
package mx.com.its.sol.sist.fsw.orm.benchmark.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author lentiummmx
 *
 */
@Entity
@Table(name="Role")
// @AttributeOverride(name = "id", column = @Column(name = "role_id", nullable = false, columnDefinition = "BIGINT UNSIGNED") )
@SequenceGenerator(name = "role_id_sequence_generator", sequenceName = "role_seq", allocationSize = 1)
public class Role extends AuditEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3963154944883572762L;
	
	public static final String ROLE_PREFIX = "ROLE_";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="role_id_sequence_generator")
	@Basic(optional = false)
	@Column(name = "role_id", nullable = false)
	protected Long id;
	
	private String name;
	
	private String description;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role [name=" + name + ", description=" + description + "]";
	}

}
