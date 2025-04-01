job('Java Maven App DSL') {
    description('Java Maven App con DSL para el curso de Jenkins')
    scm {
        git('https://github.com/ftnunez/simple-java-maven-app.git', 'main') { node ->
            node / gitConfigName('ftnunez')
            node / gitConfigEmail('ftnunez@asf.gob.mx')
        }
    }
    steps {
        maven {
          mavenInstallation('mavenjenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('mavenjenkins')
          goals('test')
        }
        shell('''
          echo "Entrega: Desplegando la aplicación" 
          java -jar "%WORKSPACE%\Java Maven App DSL\target\my-app-1.0-SNAPSHOT.jar"
        ''')  
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')
    }
}
