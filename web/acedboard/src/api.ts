const BASE = "http://localhost:8080/api";
export async function getProjects(){
    const res = await fetch(`${BASE}/projects`);
    if(!res.ok) throw new Error("Failed to fetch projects");
    return res.json();

}

export async function getTasks(projectId: string){
    const res = await fetch(`${BASE}/projects/${projectId}/tasks`);
    if(!res.ok) throw new Error("Failed to fetch tasks");
    return res.json();
}