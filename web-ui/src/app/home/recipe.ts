export class Recipe {
    name: string;
      description: string;
      instructions: string;
      minutes: number;
      personnes: number;
      userId: string;
  
    constructor(name, description, instructions, minutes, personnes, userId) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.minutes = minutes;
        this.personnes = personnes;
        this.userId = userId;
    }
  }
  