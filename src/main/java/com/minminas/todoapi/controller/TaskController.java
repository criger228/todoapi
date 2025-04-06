package com.minminas.todoapi.controller;

import com.minminas.todoapi.model.Task;
import com.minminas.todoapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks", description = "Endpoints para la gestión de tareas (CRUD)")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Crear una nueva tarea",
            description = "Crea una nueva tarea en el sistema con los datos proporcionados"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarea creada exitosamente",
            content = @Content
    )
    @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos",
            content = @Content
    )
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.ok(createdTask);
    }

    @Operation(
            summary = "Obtener todas las tareas",
            description = "Devuelve la lista de todas las tareas registradas en el sistema"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de tareas retornada exitosamente",
            content = @Content
    )
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Obtener una tarea por su ID",
            description = "Retorna la información de una tarea específica, buscándola por su ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarea encontrada",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarea no encontrada",
            content = @Content
    )
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Actualizar una tarea existente",
            description = "Modifica los datos de una tarea existente utilizando su ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tarea actualizada exitosamente",
            content = @Content
    )
    @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarea no encontrada",
            content = @Content
    )
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        try {
            Task updatedTask = taskService.updateTask(id, task);
            return ResponseEntity.ok(updatedTask);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(
            summary = "Eliminar una tarea",
            description = "Elimina una tarea existente mediante su ID"
    )
    @ApiResponse(
            responseCode = "204",
            description = "Tarea eliminada exitosamente",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Tarea no encontrada",
            content = @Content
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
