package com.example.a7minuteworkout

class Constants {
    companion object{
        fun defaultExerciseList():ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingjack = ExerciseModel(1,"Jumping Jack",
                R.drawable.ic_jumping_jacks,
                false,
                false
                )
            exerciseList.add(jumpingjack)

            val wallsit = ExerciseModel(2,"Wall sit",
                R.drawable.ic_wall_sit,
                false,
                false
            )
            exerciseList.add(wallsit)

            val pushups = ExerciseModel(3,"Push Ups",
                R.drawable.ic_push_up,
                false,
                false
            )
            exerciseList.add(pushups)

            val abdominalcrunch = ExerciseModel(4,"Abdominal Crunch",
                R.drawable.ic_abdominal_crunch,
                false,
                false
            )
            exerciseList.add(abdominalcrunch)

            val stepuponchair = ExerciseModel(5,"Step Up Onto Chair",
                R.drawable.ic_step_up_onto_chair,
                false,
                false
            )
            exerciseList.add(stepuponchair)

            val squat = ExerciseModel(6,"Squats",
                R.drawable.ic_squat,
                false,
                false
            )
            exerciseList.add(squat)

            val tricepdiponchair = ExerciseModel(7,"Triceps Dip On Chair",
                R.drawable.ic_triceps_dip_on_chair,
                false,
                false
            )
            exerciseList.add(tricepdiponchair)


            val plank = ExerciseModel(8,"Plank",
                R.drawable.ic_plank,
                false,
                false
            )
            exerciseList.add(plank)

            val highkneesrunning = ExerciseModel(9,"High Knees Running In Place",
                R.drawable.ic_high_knees_running_in_place,
                false,
                false
            )
            exerciseList.add(highkneesrunning)

            val Lunges = ExerciseModel(10,"Lungs",
                R.drawable.ic_lunge,
                false,
                false
            )
            exerciseList.add(Lunges)

            val pushupsandrotation = ExerciseModel(11,"PushUps And Rotation",
                R.drawable.ic_push_up_and_rotation,
                false,
                false
            )
            exerciseList.add(pushupsandrotation)

            val sideplank = ExerciseModel(12,"Side Planks",
                R.drawable.ic_side_plank,
                false,
                false
            )
            exerciseList.add(sideplank)

            return exerciseList

        }
    }
}