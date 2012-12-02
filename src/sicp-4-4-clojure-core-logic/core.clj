(ns sicp-4-4-clojure-core-logic.core
  (:refer-clojure :exclude  [== = > < >= <=]))

(use 'clojure.core.logic 'clojure.core.logic.arithmetic)

(defrel address person where)
(defrel job person job)
(defrel salary person salary)
(defrel supervisor person supervisor)
(defrel can-do-job superjob job)

(fact address '(Bitdiddle Ben) '(Slumerville (Ridge Road) 10))
(fact job '(Bitdiddle Ben) '(computer wizard))
(fact salary '(Bitdiddle Ben) 60000)
(fact address '(Hacker Alyssa P) '(Cambridge (Mass Ave) 78))
(fact job '(Hacker Alyssa P) '(computer programmer))
(fact salary '(Hacker Alyssa P) 40000)
(fact supervisor '(Hacker Alyssa P) '(Bitdiddle Ben))
(fact address '(Fect Cy D) '(Cambridge (Ames Street) 3))
(fact job '(Fect Cy D) '(computer programmer))
(fact salary '(Fect Cy D) 35000)
(fact supervisor '(Fect Cy D) '(Bitdiddle Ben))
(fact address '(Tweakit Lem E) '(Boston (Bay State Road) 22))
(fact job '(Tweakit Lem E) '(computer technician))
(fact salary '(Tweakit Lem E) 25000)
(fact supervisor '(Tweakit Lem E) '(Bitdiddle Ben))
(fact address '(Reasoner Louis) '(Slumerville (Pine Tree Road) 80))
(fact job '(Reasoner Louis) '(computer programmer trainee))
(fact salary '(Reasoner Louis) 30000)
(fact supervisor '(Reasoner Louis) '(Hacker Alyssa P))
(fact supervisor '(Bitdiddle Ben) '(Warbucks Oliver))
(fact address '(Warbucks Oliver) '(Swellesley (Top Heap Road)))
(fact job '(Warbucks Oliver) '(administration big wheel))
(fact salary '(Warbucks Oliver) 150000)
(fact address '(Scrooge Eben) '(Weston (Shady Lane) 10))
(fact job '(Scrooge Eben) '(accounting chief accountant))
(fact salary '(Scrooge Eben) 75000)
(fact supervisor '(Scrooge Eben) '(Warbucks Oliver))
(fact address '(Cratchet Robert) '(Allston (N Harvard Street) 16))
(fact job '(Cratchet Robert) '(accounting scrivener))
(fact salary '(Cratchet Robert) 18000)
(fact supervisor '(Cratchet Robert) '(Scrooge Eben))
(fact address '(Aull DeWitt) '(Slumerville (Onion Square) 5))
(fact job '(Aull DeWitt) '(administration secretary))
(fact salary '(Aull DeWitt) 25000)
(fact supervisor '(Aull DeWitt) '(Warbucks Oliver))

(fact can-do-job '(computer wizard) '(computer programmer))
(fact can-do-job '(computer wizard) '(computer technician))

(run* [x]
      (job x '(computer programmer)))

(run* [q]
      (fresh [x y]
             (address x y)
             (== q [x y])
             ))

(run* [q]
      (fresh [x y]
             (job x y)
             (firsto y 'computer)
             (== q [x y])
             ))

(run* [q]
      (fresh [person where]
             (job person '(computer programmer))
             (address person where)
             (== q [person where])))

(run* [q]
      (conde [(supervisor q '(Bitdiddle Ben))]
             [(supervisor q '(Hacker Alyssa P))]))

(defn noto [x]
      (conda [x fail] [succeed succeed]))

(run* [x]
      (supervisor x '(Bitdiddle Ben))
      (noto (job x '(computer programmer))))

(run* [q]
      (fresh [person amount]
             (salary person amount)
             (> amount 30000)
             (== q person)
      ))

(defn lives-near [person-1 person-2]
      (fresh [town where-1 where-2]
             (address person-1 where-1)
             (address person-2 where-2)
             (firsto where-1 town)
             (firsto where-2 town)
             (noto (== person-1 person-2))))

(run* [q]
      (fresh [p-1 p-2]
             (lives-near p-1 p-2)
             (== q [p-1 p-2])))

(defn wheel [person]
      (fresh [middle-manager x]
             (supervisor middle-manager person)
             (supervisor x middle-manager)))

(run* [q]
      (wheel q))

(defn outranked-by [staff-person boss]
      (fresh [middle-manager]
             (conde [(supervisor staff-person boss)]
                    [(supervisor staff-person middle-manager)
                     (outranked-by middle-manager boss)])))


(run* [q]
      (fresh [x y]
             (outranked-by x y)
             (== q [x y])))

(defne append-to-form [x y z]
       ([[] ?y ?y]
         succeed)
       ([_ _ _]
         (fresh [f xr zr]
                (firsto x f)
                (firsto z f)
                (resto x xr)
                (resto z zr)
                (append-to-form xr y zr))))


(run* [q]
      (append-to-form [] [3 4] q))

(run* [q]
      (append-to-form [1 2] [3 4] q))

(run* [q]
      (append-to-form '(a b) '(c d) q))
(run* [q]
      (append-to-form '(a b) q '(a b c d)))

(run* [q]
      (fresh [x y]
             (append-to-form x y '(a b c d))
             (== q [x y])
             ))
