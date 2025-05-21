# AppBudget
Application de gestion d'un budget personnel.

### Schema de l'application

![Schéma de l'application.](./schema.jpg "Schéma du l'appilcation pour la gestion d'un budget personnel.")

Nous avons en :
  1) Un menu pour ouvrir, sauvegarder un budget, modifier l'apparence de la page et quitter l'appliaction
  2) Le libellé de la transaction.
  2) Le montant de la transaction.
  3) La date de la transaction.
  4) Le type de la transaction.
  5) Tableau du budget personnel avec les totaux des actifs et passifs plus, le solde du compte.

### Planning

![Planning de l'application.](./planning.png "Planning de l'appilcation pour la gestion d'un budget personnel.")

### Structure du projet
```
└── Personal_Budget
    ├── .gitignore
    ├── schema.jpg
    ├── planning.png
    ├── README.md
    ├── Application.class
    │   └──  constructor method
    ├── WindowPrincipal.class
    │   ├── constructor method
    │   └── actionPerformed method
    ├── WindowMenu.class
    │   └──  constructor method
    ├── WindowToolBar.class
    │   └── constructor method
    ├── WindowTable.class
    │   ├── constructor method
    │   ├── AddValue method
    │   └── RemoveValue method
    ├── WindowButton.class
    │   └─ constructor method
    └── OpenAndSave.class
        ├── OpenFile method
        └── SaveFile method
```