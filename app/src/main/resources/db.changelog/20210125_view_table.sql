CREATE VIEW view_auction AS
SELECT
        aukcja.id AS auction_id,
        aukcja.title AS auction_title,
        aukcja.price AS auction_price,
        aukcja.description AS auction_description,
        zdjecie.link AS auction_photo
FROM
        auction aukcja, photo zdjecie
WHERE
        zdjecie.photonumber = 1 AND zdjecie.auction_id = aukcja.id;