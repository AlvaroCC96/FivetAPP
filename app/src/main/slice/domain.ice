// https://doc.zeroc.com/ice/3.7/language-mappings/java-mapping/client-side-slice-to-java-mapping/customizing-the-java-mapping
["java:package:cl.ucn.disc.pdis.fivet.zeroice", "cs:namespace:Fivet.ZeroIce"]
module model {

    /**
     * Clase Persona
     */
     ["cs:property"]
     class Persona{

        /**
         * Primary Key
         */
        int uid;

        /**
         * Rut: 189725965
         */
        string rut;

        /**
         * Nombre
         */
        string nombre;

        /**
        * The Apellido
        */
        string apellido;

        /**
         * Direccion
         */
        string direccion;

        /**
         * Telefono fijo
         */
         long telefonoFijo;

        /**
         * Telefono movil
         */
         long telefonoMovil;

        /**
         * Correo electronico
         */
         string email;

     }

     /**
      *Tipo de paciente
      */
      enum TipoPaciente{
         EXTERNO,INTERNO
      }

      /**
      *sexo del animal
      */
      enum Sexo{
         MACHO,HEMBRA
      }
     /**
      * La ficha
      */
     ["cs:property"]
     class Ficha {

         /**
          * Primary Key
          */
         int uid;

         /**
          * Numero
          */
         int numeroFicha;

         /*
         * nombrePaciente
         */
         string nombrePaciente;

         /**
         * especie del animal
         */
         string especie;

         /**
         * fecha nacimiento:
         * format: TODO
         */
         string fechaNacimiento;

        /**
        *raza del animal
        */
        string raza;

        Sexo sexo;

        /**
        *color del animal
        */
        string color;

        TipoPaciente tipoPaciente;

     }



     /**
     * Clase control
     */
     ["cs:property"]
     class Control {

     /**
     * id del control
     */
     int uid;

     /**
     * fecha del contol
     * format: TODO
     */
     string fechaControl;

     /**
      * fecha del contol proximo
      * format: TODO
      */
      string fechaProximoControl;


     /**
     * temperatura del paciente en celcius
     */
     float temperatura;

     /**
      *peso del paciente en kg
      */
      float peso;

      /**
      *altura del paciente en cm
      */
      int altura;

      /**
      *diagnostico del paciente
      */
      string diagnostico;

      /**
      * nombre del veterinario encargado
      */
      string nombreVeterinario;

      /*
      *url o nombre del archivo de la foto
      */
      string urlFoto;
    }

    /**
    * La foto de la ficha
    */
    ["cs:property"]
    class Foto{

        /**
        * id del examen
        */
        int uid;

        /*
        *url o nombre del archivo de la foto
        */
        string urlFoto;
    }

    /**
    * Examen asociado a un control
    */
    ["cs:property"]
    class Examen{

        /**
        * id del examen
        */
        int uid;

        /**
        *nombre del examen
        */
        string nombreExamen;

        /*
        *fecha en que fue tomada el examen
        *format: TODO
        */
        string fechaExamen;
    }

    /**
     * The base system.
     */
     interface TheSystem {

        /**
         * @return the diference in time between client and server.
         */
        long getDelay(long clientTime);

     }
     /**
     * interface
     */
     interface Contratos {
        /**
        * return Ficha associated a number(id)
        * @param number(id)
        * @return Ficha
        */
        Ficha obtenerFicha(int numero);

        Ficha registrarFicha (Ficha ficha);
        Persona registrarPersona (Persona persona);
        Control registrarControl (Control control);
        Foto agregarFoto (Foto foto);

         /**
         * @return the diference in time between client and server.
         */
        long getDelay(long clientTime);
     }

}
