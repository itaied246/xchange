# xchange

https://hidden-castle-86100.herokuapp.com/

Social platform for video games exchange.

## Data Validations

**GraphQL**

- Input types
- Required and optionals (`null`)
- Predefined values (`enums`)

**Spec**

- Regex
- Limits

## Development

Run the following services

- `postgresql`
```
docker run -d -e POSTGRES_PASSWORD=pass --net pg --restart always --name postgres -p 5432:5432 -e POSTGRES_DB=xchange postgres:9.6.6-alpine
```
- `pgadmin`
```
docker run -d -p 5050:5050 --name pgadmin --restart always --net pg thajeztah/pgadmin4
```

## Testing

`lein test` run all test suites.

`lein test :dev` run unit tests.