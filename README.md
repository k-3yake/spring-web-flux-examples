# spring-web-flux-examples

# DOING
大量postが可能なクライアントを作成
curl -H "Content-Type: application/json" -X POST -d '{"name":"name3"}' http://localhost:8080/city
curl -H "Content-Type: application/stream+json" -X POST -d '{"name":"name3"}' http://localhost:8080/city

# TODO
通常Restのpostの例を作成（DBに書き込むまで）
パフォーマンスを比較
FluxでもDBのブロッキングで引っ掛かるはずなので打開策を探る
