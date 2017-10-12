Start up with

helm install -n cassandra --values=values.yaml

open a notebook with

kubectl port-forward notebook-<something> 8888:8888

then localhost:8888 password ()

to find the cassandra cluster use

kubectl get pods -o wide

and take the pod id and use it as the hostname

now you can follow the instructions in the notebook and experiment!
