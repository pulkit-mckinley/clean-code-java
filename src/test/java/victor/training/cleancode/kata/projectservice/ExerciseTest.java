package victor.training.cleancode.kata.projectservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ExerciseTest {
  @Mock
  private ProjectServicesService projectServicesService;
  @Mock
  private UserService userService;
  @Mock
  private UserServiceHelper userServiceHelper;
  @Mock
  private ServiceService serviceService;

  @InjectMocks
  private Exercise exercise;

  @Test
void sendsMessageOnCreateForAdminWithSubscribedService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.ADMIN);
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServiceStatus.CREATED);
    projectServices.setService(service);
    User user = new User();

    when(projectServicesService.getProjectServicesByProjectId(any())).thenReturn(Arrays.asList(projectServices));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verifyNoMoreInteractions(userServiceHelper);
}
  @Test
  void sendsMessageOnCreateForAdmin() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.ADMIN);
    Project project = new Project();
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServiceStatus.SUBSCRIBED);
    User user = new User();

    when(projectServicesService.getProjectServicesByProjectId(any())).thenReturn(Arrays.asList(projectServices));
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser));
  }


  @Test
void sendsMessageOnCreateForNonAdmin() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.VIEW);
    projectUser.setServices(Arrays.asList("service"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServiceStatus.SUBSCRIBED);
    User user = new User();

    when(serviceService.findAll()).thenReturn(Arrays.asList(service));
    when(projectServicesService.findByServiceAndProject(any(), any())).thenReturn(Optional.of(projectServices));
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser));
  }

  @Test
void doesNotSendMessageOnCreateForNonAdminWhenNoMatchingService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.VIEW);
    projectUser.setServices(Arrays.asList("nonexistentService"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServiceStatus.SUBSCRIBED);
    User user = new User();

    when(serviceService.findAll()).thenReturn(Arrays.asList(service));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verifyNoInteractions(userServiceHelper);
}

@Test
void sendsMessageOnCreateForContributorWithSubscribedService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.CONTRIBUTOR);
    projectUser.setServices(Arrays.asList("service"));
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServiceStatus.SUBSCRIBED);
    projectServices.setService(service);
    User user = new User();

    when(serviceService.findAll()).thenReturn(Arrays.asList(service));
    when(projectServicesService.findByServiceAndProject(any(), any())).thenReturn(Optional.of(projectServices));
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(any(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser));
}

@Test
void setsServiceOnProjectServicesDTOForAdminWithSubscribedService() {
    ProjectUserDTO projectUser = new ProjectUserDTO();
    projectUser.setRole(ProjectUserRoleType.ADMIN);
    Project project = new Project();
    Service service = new Service().setName("service");
    ProjectServices projectServices = new ProjectServices();
    projectServices.setStatus(ProjectServiceStatus.SUBSCRIBED);
    projectServices.setService(service);
    User user = new User();

    when(projectServicesService.getProjectServicesByProjectId(any())).thenReturn(Arrays.asList(projectServices));
    when(userService.findByUuid(any())).thenReturn(Optional.of(user));

    exercise.sendUserMessageOnCreate(projectUser, project, MessageAction.CREATE);

    ArgumentCaptor<ProjectServicesDTO> captor = ArgumentCaptor.forClass(ProjectServicesDTO.class);
    verify(userServiceHelper, times(1)).sendUserToServicesOnCreate(captor.capture(), eq(project), eq(MessageAction.CREATE), eq(user), eq(projectUser));
    assertEquals(service, captor.getValue().service());
}
}