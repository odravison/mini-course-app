package br.odravison.sogo.minicoursesinfrasctructure.entities;

import br.odravison.sogo.minicoursesdomain.presentation.minicourse.ReadMiniCourseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @SequenceGenerator(name="mini_course_seq", allocationSize = 1, sequenceName = "mini_course_seq")
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @Fetch(FetchMode.SUBSELECT)
    private List<StudentMapped> participants = new ArrayList<>();

    @ManyToOne(targetEntity = ProfessorMapped.class, optional = false)
    private ProfessorMapped professorOwner;

    @Column(name = "deleted")
    private boolean deleted;

    public ReadMiniCourseResponse buildReadMiniCourseResponse(){
        return new ReadMiniCourseResponse(id, name, startDate, duration, vacanciesNumber, professorOwner.getId());
    }

}
