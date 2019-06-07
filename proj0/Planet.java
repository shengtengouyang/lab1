public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G=6.67*Math.pow(10,-11);
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public Planet(Planet b){
        xxPos=b.xxPos;
        yyPos=b.yyPos;
        xxVel=b.xxVel;
        yyVel=b.yyVel;
        mass=b.mass;
        imgFileName=b.imgFileName;
    }

    private double findR(Planet planet){
        return Math.pow(xxPos-planet.xxPos,2)+Math.pow(yyPos-planet.yyPos,2);
    }
    public double calcDistance(Planet planet){
        double r=findR(planet);
        return Math.pow(r, 0.5);
    }

    public double calcForceExertedBy(Planet planet){
        return (G*mass*planet.mass)/findR(planet);
    }

    public double calcForceExertedByX(Planet planet){
        return calcForceExertedBy(planet)*(planet.xxPos-xxPos)/calcDistance(planet);
    }
    
    public double calcForceExertedByY(Planet planet){
        return calcForceExertedBy(planet)*(planet.yyPos-yyPos)/calcDistance(planet);
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double f=0;
        for(Planet i:planets){
            if(!this.equals(i)){
                f+=calcForceExertedByX(i);
            }
        }
        return f;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double f=0;
        for(Planet i:planets){
            if(!this.equals(i)){
                f+=calcForceExertedByY(i);
            }
        }
        return f;
    }

    public void update(double t, double x, double y){
        double ax=x/mass;
        double ay=y/mass;
        xxVel+=ax*t;
        yyVel+=ay*t;
        xxPos+=xxVel*t;
        yyPos+=yyVel*t;
    }
    
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}