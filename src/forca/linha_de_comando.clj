(ns forca.linha-de-comando
  (:require [forca.forca :as forca]))

(defn le-palpite [estado]
  (println (forca/cria-marcarcoes estado))
  (println "Qual seu palpite? ")
  (read-line))


(defn joga [dados]
  (cond
    (forca/enforcou? dados)
    (forca/mensagem-final dados)

    (forca/venceu? dados)
    (forca/mensagem-final dados)

    :else
    (recur (forca/registra-palpite (le-palpite dados) dados))))


(let [estado-inicial (forca/cria-estado-inicial "banana")
      mensagem-final (joga estado-inicial)]
  (println mensagem-final))