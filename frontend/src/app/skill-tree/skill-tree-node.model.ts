export interface SkillTreeNodeModel {
    id: string;
    name: string;
    description: string;
    value: number;
    color?: string;
    children?: SkillTreeNodeModel[];
}
