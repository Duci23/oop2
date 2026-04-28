package me.fit.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.model.Student;
import me.fit.service.StudentService;

import java.util.List;

@Path("/student")
public class StudentResource {

    @Inject
    private StudentService studentService;

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
}