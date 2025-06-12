# AppBudget
Application de gestion d'un budget personnel.

# Cahier des charges de l'application

L'application permettra à l'utilisateur de créer et de gérer un petit budget individuel, par exemple, pour le ménage ou pour des vacances. 

Il y aura un menu "File", qui aura 4 items; "New", "Open", "Save", "Quit". Ceci permettra de créer, de modifier plusieurs budgets. Le nom du budget sera afficher dans le cadre de le fenêtre de l'application. 

L'application permettra d'écrire l'objet de la saisie, par un champ de "Libellé", un champ "Montant" et un champ "Date".

Une fois l'objet saisi, il faudra cliquer sur le bouton "Crédit" ou sur le bouton "Débit" pour enregistrer l'objet dans un fichier. 

Dans la partie de droite, il y aura une table qui permettra de visualiser les objets saisis, leurs libellés, ainsi que de supprimer une entrée si on le souhaite. 

Au bas de la table, nous afficherons les totaux des crédits et des débits, ainsi que le solde. 

Sur le haut de la table, se trouvera un menu déroulant qui permettra de sélectionner ou de filtrer les dates des objets que l'on souhaite afficher.

## Planning

![Planning de l'application.](./planning.png "Planning de l'appilcation pour la gestion d'un budget personnel.")

## Structure du projet
```
└── AppBudget
    ├── .gitignore
    ├── schema.jpg
    ├── planning.png
    ├── README.md
    ├── assets
    │   └── trash.png
    │   └── trash_gray.png
    ├── data
    │   └── budget_120625.csv
    ├── Lib
    │   └── flatlaf-3.4.jar
    └── src
        ├── Account.java
        │   └──Account
        │      ├── Account constructor
        │      ├── getDate method
        │      ├── getLibelle method
        │      ├── getCredit method
        │      └── getDebit method
        ├── BudgetController.java
        │   └── BudgetController class
        │       ├── BudgetController constructor
        │       ├── addEntry method
        │       ├── clearTable method
        │       ├── applyFilters method
        │       ├── getFilteredDates method
        │       ├── getFilteredLibelles method
        │       ├── getDay method
        │       ├── setDay method
        │       ├── getMonth method
        │       ├── setMonth method
        │       ├── getYear method
        │       ├── setYear method
        │       ├── getRowCount method
        │       ├── getColumnCount method
        │       ├── getValueAt method
        │       ├── getDate method
        │       ├── getLibelle method
        │       ├── getMonth method
        │       ├── getSoldeCredit method
        │       ├── getSoldeDebit method
        │       └── getSolde method
        ├── BudgetModel.java
        │   └── BudgetModel class
        │       ├── BudgetModel constructor
        │       ├── getColumnName method
        │       ├── getValueAt method
        │       ├── isCellEditable method
        │       ├── getRowCount method
        │       ├── getColumnCount method
        │       ├── addEntry method
        │       ├── clearTable method
        │       ├── removeRow method
        │       ├── getFilteredDates method
        │       ├── getFilteredLibelles method
        │       ├── applyFilters method
        │       ├── sumCalculation method
        │       ├── getSoldeCredit method
        │       ├── getSoldeDebit method
        │       ├── getSolde method
        │       ├── setDay method
        │       ├── setMonth method
        │       ├── setYear method
        │       ├── getDay method
        │       ├── getMonth method
        │       ├── getYear method
        │       ├── getDate method
        │       ├── getLibelle method
        │       └── addPropertyChangeListener method
        ├── DatePicker.java
        │   └── DatePicker constructor
        ├── MainApp.java
        │   └── main method
        ├── NeSaOP.java
        │   ├── NewFile method
        │   ├── OpenFile method
        │   └── SaveFile method
        ├── WindowForms.java
        │   ├─── WindowForms class
        │   │   ├── WindowForms constructor
        │   │   └── checkInput method
        │   └── IntFilter class
        │       ├── insertString constructor
        │       ├── getFilteredDates method
        │       ├── check method
        │       └── replace method
        ├── WindowMain.java
        │   └─── WindowMain class
        │        ├── WindowMain constructor
        │        └── restartUI method
        ├── WindowMenu.java
        │   └─── WindowMenu class
        │        └── WindowMenu constructor
        ├── WindowMessages.java
        │   └─── WindowMessages class
        │        └── WindowMessages constructor
        └── WindowTable.java
            ├─── WindowTable class
            │    ├── WindowTable constructor
            │    ├── propertyChange method
            │    ├── refreshButtonRenderer method
            │    ├── checkCombobox method
            │    ├── onFilterChanged method
            │    ├── updateComboBoxes method
            │    ├── getDay method
            │    ├── getFilteredDates method
            │    ├── check method
            │    └── replace method
            └── ButtonEditor class
                ├── ButtonEditor constructor
                ├── getTableCellEditorComponent method
                └── getCellEditorValue method  
```

## Compilation de l'application
    javac -cp ".;../Lib/flatlaf-3.4.jar" MainApp.java
    java -cp ".;../Lib/flatlaf-3.4.jar" MainApp

## Manuel d’utilisateur 

#### Schema de l'application

    ![Schéma de l'application.](./schema.jpg "Schéma du l'appilcation pour la gestion d'un budget personnel.")

    Nous avons en :
    1) Le nom du budget 
    2) Un menu pour créer , ouvrir, sauvegarder un budget et quitter l'appliaction
    3) Le libellé de la transaction.
    4) Le montant de la transaction.
    5) La date de la transaction.
    6) Le type de la transaction.
    7) Tableau du budget personnel avec :\
        7.1 les totaux des actifs et passifs plus, le solde du compte\
        7.2 la table qui affiche les saisies\
        7.3 le sélecteur de filtre

### Amélioration de l'application
    Ajouter dans menu un onglet sauver sous.
    Ajouter dans le menu un onglet manuel d'utilisateur.
    Ajouter  à l'application le manuel d'utilisateur
    Ajouter le type de dépense exemple (loisir, nourriture, fixe, ext..)
    Améliorer l'input montant pour interdir de commencer avec un zéro.
    Améliorer le message d'erreurs sur les valeurs nons renseigné. Ajouter quelle champs n'est pas correcte et le mettre en surbrillance.

### Bugs connus
    Nous avons réussi a résoudre tout les bugs.

### Auto critique de notre code
    Pas d'idée


### Les difficulté renconté
    La mise en place de la table avec ces rendues.
    La mise en place du systèem MVC ave la table.
    La mise en place d'un filtre dynamique croisé entre les deux combobox date et libellé.
    La difficulté de définir les taches de chacuns (travail en commun).