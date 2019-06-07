public class NBody{
    public static double readRadius(String file){
        In reader=new In(file);
        reader.readLine();
        return Double.parseDouble(reader.readLine());
    }
    public static Planet[] readPlanets(String file){
        In reader=new In(file);
        int rows=Integer.parseInt(reader.readLine());
        reader.readLine();
        String []strings=reader.readAllStrings();
        Planet [] planets=new Planet[rows];
        for (int i=0;i<rows;i++){
            double xP=Double.parseDouble(strings[6*i]);
            double yP=Double.parseDouble(strings[6*i+1]);
            double xV=Double.parseDouble(strings[6*i+2]);
            double yV=Double.parseDouble(strings[6*i+3]);
            double m=Double.parseDouble(strings[6*i+4]);
            planets[i]=new Planet(xP,yP,xV,yV,m,strings[6*i+5]);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        Planet[] planets=readPlanets(filename);
        double radius=readRadius(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0,0, "images/starfield.jpg");
        for(Planet i:planets){
            i.draw();
        }
        StdDraw.enableDoubleBuffering();
        double time=0;
        for(time=0;time<T;time+=dt){
            double[] xForces=new double[planets.length];
            double [] yForces=new double[planets.length];
            for(int j=0;j<planets.length;j++){        
                xForces[j]=planets[j].calcNetForceExertedByX(planets);
                yForces[j]=planets[j].calcNetForceExertedByY(planets);
            }
            for(int i=0;i<planets.length;i++){
                planets[i].update(time, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0,0, "images/starfield.jpg");
            for(Planet i:planets){
                i.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}