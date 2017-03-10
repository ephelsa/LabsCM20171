##Versiones del Lab-2 ##

> Un branch es una ramificación del proyecto.
> Aquí encontrarás más información sobre [branch](https://styde.net/sistema-de-ramificaciones-en-git/) 

###Nombre del branch: VersionesLab2

Aquí **pondremos todas las versiones y cambios que hagamos** en el proyecto.

###Crear el branch en el repositorio local:

`git branch VersionesLab2` 

Cambiar entre branch:
	
`git checkout VersionesLab2`

Se verifica que sí estemos en este branch, utilizando **`git branch`**, y aparecerá algo como:

~~~

*\ `VersionesLab2`
`master`

~~~

Y lo que resta es subir los archivos de forma normal, con la diferencia de que el proyecto final se mostrará en el *branch* `master`. Para ello se utiliza `git merge VersionesLab2` para fusionar el *branch* `master -> VersionesLab2`.
___

> **Nota:** *LEEME.md* está en lenguaje de marcado MarkDown.
> [Más información de MarkDown](https://markdown.es)
