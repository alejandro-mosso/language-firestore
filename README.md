# language-firestore

#Heroku integration
https://languagelab-firestore.herokuapp.com
<br/><code><b>$</b> heroku login</code>
<br/><code><b>$</b> heroku git:remote -a languagelab-firestore</code> # Associate this app with haroku 
<br/><code><b>$</b> heroku config:set WEB_CONCURRENCY=3 </code>
<br/><code><b>$</b> heroku ps:scale web=1 </code>

<br/><code><b>$</b> heroku logs --tail --app languagelab-service</code>
<br/><code><b>$</b> git status </code>
<br/><code><b>$</b> git commit --allow-empty -m "Adjust buildpacks on Heroku" </code>
<br/><code><b>$</b> git push heroku master </code> # Compile project
<br/><code><b>$</b> git push heroku HEAD:master --force </code> # Compile project
