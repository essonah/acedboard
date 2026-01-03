import { useQuery } from '@tanstack/react-query'
import {
  Container,
  Typography,
  Box,
  Card,
  CardContent,
  CircularProgress,
  Alert,
  Grid,
  Chip,
  Paper,
} from '@mui/material'
import { getProjects } from './api'

interface Project {
  id: number
  userId: number
  projectName: string
  description?: string
  createdAt: string
}

function App() {
  const { data: projects, isLoading, error } = useQuery<Project[]>({
    queryKey: ['projects'],
    queryFn: getProjects,
  })

  return (
    <Container maxWidth="lg" sx={{ py: 4 }}>
      <Box sx={{ mb: 4 }}>
        <Typography variant="h3" component="h1" gutterBottom fontWeight="bold" color="primary">
          AcedBoard
        </Typography>
        <Typography variant="h6" color="text.secondary" gutterBottom>
          Welcome to AcedBoard! Manage your projects and tasks efficiently.
        </Typography>
      </Box>

      <Box sx={{ mb: 3, display: 'flex', alignItems: 'center', gap: 1 }}>
        <Typography variant="h5" component="h2" fontWeight="medium">
          Projects
        </Typography>
        {projects && projects.length > 0 && (
          <Chip label={projects.length} color="primary" size="small" />
        )}
      </Box>

      {isLoading && (
        <Box sx={{ display: 'flex', justifyContent: 'center', my: 4 }}>
          <CircularProgress />
        </Box>
      )}

      {error && (
        <Alert severity="error" sx={{ mb: 3 }}>
          Error loading projects: {error.message}
        </Alert>
      )}

      {projects && projects.length === 0 && (
        <Paper
          elevation={0}
          sx={{
            p: 4,
            textAlign: 'center',
            backgroundColor: 'grey.50',
            borderRadius: 2,
          }}
        >
          <Typography variant="h6" color="text.secondary" gutterBottom>
            No projects found
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Create your first project to get started!
          </Typography>
        </Paper>
      )}

      {projects && projects.length > 0 && (
        <Grid container spacing={3}>
          {projects.map((project) => (
            <Grid item xs={12} sm={6} md={4} key={project.id}>
              <Card
                elevation={2}
                sx={{
                  height: '100%',
                  display: 'flex',
                  flexDirection: 'column',
                  transition: 'transform 0.2s, box-shadow 0.2s',
                  '&:hover': {
                    transform: 'translateY(-4px)',
                    boxShadow: 6,
                  },
                }}
              >
                <CardContent sx={{ flexGrow: 1 }}>
                  <Typography variant="h6" component="h3" gutterBottom fontWeight="medium">
                    {project.projectName}
                  </Typography>
                  {project.description && (
                    <Typography
                      variant="body2"
                      color="text.secondary"
                      sx={{ mb: 2, minHeight: '3em' }}
                    >
                      {project.description}
                    </Typography>
                  )}
                  <Typography variant="caption" color="text.secondary">
                    Created: {new Date(project.createdAt).toLocaleDateString('en-US', {
                      year: 'numeric',
                      month: 'long',
                      day: 'numeric',
                    })}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          ))}
        </Grid>
      )}
    </Container>
  )
}

export default App
