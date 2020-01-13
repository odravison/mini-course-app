package br.odravison.sogo.minicoursesinfrasctructure.entities;

import br.odravison.sogo.minicoursesdomain.presentation.minicourse.ReadMiniCourseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mini_course")
@SQLDelete(sql = "UPDATE mini_course SET deleted = true WHERE id = ?")
@Where(clause = BaseMappedEntity.WHERE_DELETED_CLAUSE)
public class MiniCourseMapped extends BaseMappedEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mini_course_seq")
    @SequenceGenerator(name="mini_course_seq", sequenceName = "mini_course_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "vacancies_number")
    private Integer vacanciesNumber;

    @ManyToOne(targetEntity = ProfessorMapped.class, optional = false)
    private ProfessorMapped professorOwner;

    public ReadMiniCourseResponse buildReadMiniCourseResponse(){
        return new ReadMiniCourseResponse(id, name, startDate, duration, vacanciesNumber, professorOwner.getId());
    }

}
