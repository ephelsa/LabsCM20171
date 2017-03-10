##Versiones del Lab-2 ##

> Un branch es una ramificación del proyecto.
> Aquí encontrarás más información sobre [branch.](https://styde.net/sistema-de-ramificaciones-en-git/) 

###Nombre del branch: VersionesLab2

Aquí **pondremos todas las versiones y cambios que hagamos** en el proyecto.

###Subir archivos al branch

Hay dos formas de hacerlo.

#### Primera forma

La *primera* forma, es que ***todo*** lo que se le haga `push`, tenemos que especificar hacia a dónde se hará, es decir:

`git push origin [branch]`	

En nuestro caso sería *VersionesLab2*. Quedaría:

`git push origin VersionesLab2`	

#### Segunda forma

En la *segunda* forma, tenemos que crear el branch en el *repositorio local*:

`git branch VersionesLab2` 

Cambiar entre branch:
	
`git checkout VersionesLab2`

Se verifica que sí estemos en este branch, utilizando **`git branch`**, y aparecerá algo como:

* `VersionesLab2`  
`master`

Y a la hora de hacerle `push` a los archivos basta con solo escribir:

`git push`	*//Esto nos subirá de una vez los archivos al branch.*

Una vez terminado el proyecto final, este se mostrará en el *branch* `master`. Para ello se utiliza `git merge VersionesLab2` para fusionar el *branch* `master -> VersionesLab2`.
___

> **Nota:** *LEEME.md* está en lenguaje de marcado MarkDown.
> [Más información de MarkDown.](https://markdown.es)
