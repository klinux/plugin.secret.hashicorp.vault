# Plugin para o GoCD para usar o Hashicorp Vault como secrets

Este plugin habilita o GoCD para usar o Hashicorp Vault como servidor de secrets.

# Compilar o plugin

```sh
./gradlew clean test assemble
```

Ou execute

```sh
make all
```
Par buildar a lib e colocar na pasta para o GoCD usar a lib.

# Rodar o vault e unseal

## criar diretorios para manter dados
```sh
mkdir -p data/consul
mkdir -p data/consulw
mkdir -p data/godata
mkdir -p data/plugins

chmod -R 777 data/consul
chmod -R 777 data/consulw
chmod -R 777 data/godata
chmod -R 777 data/plugins
```

## executar o docker-compose para subir os servicos

```sh
docker-compose up -d
```

## acessar o container do vault

```sh
docker-compose exec vault sh
```

## iniciar o vault

```sh
vault operator init
```

## unseal o vault

```sh
for key in <unseal_key1> <unseal_key2> <unseal_key3>; do vault operator unseal "${key}"; done
```

# Licença

```plain
Copyright 2019 ThoughtWorks, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
