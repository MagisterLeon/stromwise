export interface SkillTreeNodeModel {
    id: string;
    name: string;
    value: number;
    children?: SkillTreeNodeModel[];
}
