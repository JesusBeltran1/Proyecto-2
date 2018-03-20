class Ladrillo8 { 
  Body body;
  float r = 16;
  
  Ladrillo8() {
    makeBody(new Vec2(580,80),0,new Vec2(0, 0),0);
    body.setUserData(this);
  } 
  
  void makeBody(Vec2 position, float angle, Vec2 vel,float omega) {
     
    PolygonShape ps = new PolygonShape();
    float ladrillo_8W = box2d.scalarPixelsToWorld(10);
    float ladrillo_8H = box2d.scalarPixelsToWorld(20);
     
    ps.setAsBox(ladrillo_8W, ladrillo_8H);
    
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(position));
   
    bd.angle = angle; 
        
    body = box2d.createBody(bd);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = ps;
    fd.density = 10;
    fd.friction = 3;

    body.createFixture(fd);
    
    body.setLinearVelocity(vel);
    body.setAngularVelocity(omega);
  }
  
  void display() { 
    Vec2 pos = box2d.getBodyPixelCoord(body);
    float a = body.getAngle();
    Fixture f = body.getFixtureList();
    PolygonShape ps = (PolygonShape) f.getShape();
    
    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(-a);
    stroke(#969696);
    strokeWeight(3);
    fill(#B73D3D);
    beginShape();
    for (int i = 0; i < ps.getVertexCount(); i++) {
      Vec2 v = box2d.vectorWorldToPixels(ps.getVertex(i));
      vertex(v.x, v.y);
    }
    endShape(CLOSE);
    popMatrix();
  }
}
