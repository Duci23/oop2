package me.fit.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.model.Student;
import me.fit.service.StudentService;

import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import me.fit.client.IpClient;
import me.fit.client.TimeClient;
import me.fit.dto.TimeResponse;
import me.fit.model.TimezoneInfo;
import jakarta.persistence.EntityManager;

import java.util.List;

@Path("/student")
public class StudentResource {

    @Inject
    private StudentService studentService;

    @Inject
    @RestClient
    IpClient ipClient;

    @Inject
    @RestClient
    TimeClient timeClient;

    @Inject
    EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addStudent")
    public String addStudent(Student student) {
        studentService.createStudent(student);
        return "OK";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllStudents")
    //@RolesAllowed("admin")

    public Response getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return Response.ok().entity(students).build();
    }

    //
    // PRETRAGA 1 - preko PathParam
    // /student/getStudentById/1
    //
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getStudentById/{id}")
    public Response getStudentById(@PathParam("id") Long id) {
        Student student = studentService.getStudentById(id);
        return Response.ok(student).build();
    }

    //
    // PRETRAGA 2 - preko QueryParam
    // /student/searchByName?name=Adnan
    //
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchByName")
    public Response searchByName(@QueryParam("name") String name) {
        List<Student> students = studentService.searchByName(name);
        return Response.ok(students).build();
    }

    @GET
    @Path("/{id}/courses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentCourses(@PathParam("id") Long id) {

        Student student = studentService.getStudentById(id);

        return Response.ok(student.getCourses()).build();
    }

    @GET
    @Path("/{id}/instructors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentInstructors(@PathParam("id") Long id) {

        Student student = studentService.getStudentById(id);

        return Response.ok(student.getInstructors()).build();
    }


    @GET
    @Path("/getTimezoneByIP")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {

        Student student = studentService.getStudentById(userId);

        if (student == null) {
            return Response.status(404).entity("Student not found").build();
        }

        String ip = ipClient.getIp();

        String response = timeClient.getTime(ip);

        return Response.ok(response).build();
    }

    //
    //  TimezoneInfo info = new TimezoneInfo();
    //  info.setTimeZone(response.timeZone);
    //  info.setDateTime(response.dateTime);
    //info.setCountryName(response.countryName);
    //  info.setCityName(response.cityName);
    //  info.setStudent(student);

    //  student.getTimezoneInfos().add(info);

    //  return Response.ok(info).build();
    //}


}