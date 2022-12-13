(ns forca.forca
  (:require [clojure.set :as conjuntos]))


(defn cria-estado-inicial [segredo]
  {:segredo  segredo
   :palpites #{}
   :erros    0})


(defn cria-marcarcoes [dados]
  (->> (:segredo dados)
       (map str)
       (mapv (fn [letra]
               (if (contains? (:palpites dados) letra) letra "_")))))


(defn enforcou? [dados]
  (= (:erros dados) 6))


(defn acertou-palpite? [dados palpite]
  (clojure.string/includes? (:segredo dados) palpite))


(defn venceu? [dados]
  (conjuntos/subset? (into #{} (map str (:segredo dados)))
                     (:palpites dados)))


(defn registra-palpite [palpite dados]
  (let [estado-atualizado (update dados :palpites conj palpite)]
    (if (acertou-palpite? estado-atualizado palpite)
      estado-atualizado
      (update estado-atualizado :erros inc))))


(defn mensagem-final [dados]
  (if (enforcou? dados)
    (str  "Você perdeu!!!! A palavra secreta é " (:segredo dados))
    "VOCÊ VENCEU!!!"))


