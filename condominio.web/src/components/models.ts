export class User {
  id!: number;
  dni!: number;
  name!: string;
  lastName!: string;
  email!: string;
}

export class Department {
  id!: number;
  number!: number;
  user!: User;
}

export class Tower {
  id!: number;
  identification!: string;
  departments: Department[] = []
}

export class Condominio {
  id!: number;
  name!: string;
  address!: string;
  towers: Tower[] = [];
}
